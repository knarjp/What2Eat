package com.design.senior.what2eat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.design.senior.what2eat.DatabaseComponents.AppDatabase;
import com.design.senior.what2eat.DatabaseComponents.Entities.Meal;
import com.design.senior.what2eat.DatabaseComponents.Enums.AllergyType;
import com.design.senior.what2eat.DatabaseComponents.Enums.DietType;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MealEditorActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    private AppDatabase appDatabase;

    private Meal meal;

    private boolean needsToSave;
    private boolean isNewMeal;

    private EditText nameEditText;
    private EditText ingredientsEditText;
    private EditText recipeEditText;
    private EditText caloriesEditText;

    private ImageView mealImageView;

    private CheckBox milkCheck;
    private CheckBox eggsCheck;
    private CheckBox fishCheck;
    private CheckBox shellfishCheck;
    private CheckBox treenutsCheck;
    private CheckBox peanutsCheck;
    private CheckBox wheatCheck;
    private CheckBox soyCheck;

    private RadioGroup dietsRadioGroup;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mealeditor_layout);

        Intent incomingIntent = getIntent();
        meal = (Meal) incomingIntent.getParcelableExtra("meal");
        isNewMeal = (boolean) incomingIntent.getBooleanExtra("isNewMeal", false);

        needsToSave = false;

        appDatabase = AppDatabase.getAppDatabase(getApplicationContext());

        nameEditText = (EditText) findViewById(R.id.meal_editor_name_entry);
        ingredientsEditText = (EditText) findViewById(R.id.meal_editor_ingredients_entry);
        recipeEditText = (EditText) findViewById(R.id.meal_editor_recipe_entry);
        caloriesEditText = (EditText) findViewById(R.id.meal_editor_calories_entry);

        mealImageView = (ImageView) findViewById(R.id.meal_editor_image);;

        milkCheck = (CheckBox) findViewById(R.id.allergies_milk_checkbox);
        eggsCheck = (CheckBox) findViewById(R.id.allergies_eggs_checkbox);
        fishCheck = (CheckBox) findViewById(R.id.allergies_fish_checkbox);
        shellfishCheck = (CheckBox) findViewById(R.id.allergies_shellfish_checkbox);
        treenutsCheck = (CheckBox) findViewById(R.id.allergies_treenuts_checkbox);
        peanutsCheck = (CheckBox) findViewById(R.id.allergies_peanuts_checkbox);
        wheatCheck = (CheckBox) findViewById(R.id.allergies_wheat_checkbox);
        soyCheck = (CheckBox) findViewById(R.id.allergies_soy_checkbox);

        dietsRadioGroup = (RadioGroup) findViewById(R.id.options_radio_group);

        saveButton = (Button) findViewById(R.id.saveButton);

        setOnClickListeners();

        if(isNewMeal) {
            // load default imageview image for a new meal
            Uri defaultImagePath = Uri.parse("android.resource://com.design.senior.what2eat/mipmap/ic_launcher");
            mealImageView.setImageURI(defaultImagePath);

            // set the new meal's image to the default image
            Bitmap bitmap = ((BitmapDrawable) mealImageView.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            meal.setPicture(byteArray);
        } else {
            // put existing meal data into all the views
            try {
                byte[] imageByteArray = meal.getPicture();
                Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);

                mealImageView.setImageBitmap(imageBitmap);
            } catch (Exception e) {
                // do nothing if image cannot be loaded (set default image)
                Uri defaultImagePath = Uri.parse("android.resource://com.design.senior.what2eat/mipmap/ic_launcher");
                mealImageView.setImageURI(defaultImagePath);
            }

            nameEditText.setText(meal.getName());
            ingredientsEditText.setText(meal.getIngredients());
            recipeEditText.setText(meal.getRecipe());
            caloriesEditText.setText(String.valueOf(meal.getCaloricAmount()));

            if(meal.getAllergiesEnum().contains(AllergyType.MILK)) {
                milkCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.EGGS)) {
                eggsCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.FISH)) {
                fishCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.SHELLFISH)) {
                shellfishCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.TREENUTS)) {
                treenutsCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.PEANUTS)) {
                peanutsCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.WHEAT)) {
                wheatCheck.setChecked(true);
            }
            if(meal.getAllergiesEnum().contains(AllergyType.SOY)) {
                soyCheck.setChecked(true);
            }

            switch(meal.getDietTypeEnum()) {
                case NONE:
                    dietsRadioGroup.check(R.id.options_none_radio);
                    break;
                case VEGAN:
                    dietsRadioGroup.check(R.id.options_vegan_radio);
                    break;
                case PALEO:
                    dietsRadioGroup.check(R.id.options_paleo_radio);
                    break;
                case VEGETARIAN:
                    dietsRadioGroup.check(R.id.options_vegetarian_radio);
                    break;
                case PESCETARIAN:
                    dietsRadioGroup.check(R.id.options_pescetarian_radio);
                    break;
                default:
                    dietsRadioGroup.check(R.id.options_none_radio);
                    break;
            }

            needsToSave = false;
        }
    }

    private void setOnClickListeners() {
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no functionality needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no functionality needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                needsToSave = true;
                meal.setName(s.toString());
            }
        });

        ingredientsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no functionality needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no functionality needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                needsToSave = true;
                meal.setIngredients(s.toString());
            }
        });

        recipeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no functionality needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no functionality needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                needsToSave = true;
                meal.setRecipe(s.toString());
            }
        });

        caloriesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // no functionality needed
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // no functionality needed
            }

            @Override
            public void afterTextChanged(Editable s) {
                needsToSave = true;
                meal.setCaloricAmount(Integer.parseInt(s.toString()));
            }
        });

        mealImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(ContextCompat.checkSelfPermission(MealEditorActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MealEditorActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        // need no explanation, just access the gallery
                    } else {
                        ActivityCompat.requestPermissions(MealEditorActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                RESULT_LOAD_IMAGE);
                    }
                } else {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, RESULT_LOAD_IMAGE);

                    needsToSave = true;
                }
            }
        });

        milkCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(milkCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.MILK);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.MILK);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        eggsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(eggsCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.EGGS);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.EGGS);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        fishCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fishCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.FISH);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.FISH);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        shellfishCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shellfishCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.SHELLFISH);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.SHELLFISH);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        treenutsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(treenutsCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.TREENUTS);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.TREENUTS);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        peanutsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(peanutsCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.PEANUTS);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.PEANUTS);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        wheatCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(wheatCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.WHEAT);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.WHEAT);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        soyCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(soyCheck.isChecked()) {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.add(AllergyType.SOY);
                    meal.setAllergiesEnum(allergyTypes);
                } else {
                    List<AllergyType> allergyTypes = meal.getAllergiesEnum();
                    allergyTypes.remove(AllergyType.SOY);
                    meal.setAllergiesEnum(allergyTypes);
                }

                needsToSave = true;
            }
        });

        dietsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.options_none_radio:
                        meal.setDietTypeEnum(DietType.NONE);

                        needsToSave = true;
                        break;
                    case R.id.options_vegan_radio:
                        meal.setDietTypeEnum(DietType.VEGAN);

                        needsToSave = true;
                        break;
                    case R.id.options_vegetarian_radio:
                        meal.setDietTypeEnum(DietType.VEGETARIAN);

                        needsToSave = true;
                        break;
                    case R.id.options_pescetarian_radio:
                        meal.setDietTypeEnum(DietType.PESCETARIAN);

                        needsToSave = true;
                        break;
                    case R.id.options_paleo_radio:
                        meal.setDietTypeEnum(DietType.PALEO);

                        needsToSave = true;
                        break;
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap image = BitmapFactory.decodeFile(picturePath);

            mealImageView.setImageBitmap(image);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            meal.setPicture(byteArray);
        }
    }

    @Override
    public void onBackPressed() {
        saveMeal();
        super.onBackPressed();
    }

    private void saveMeal() {
        if(needsToSave) {
            if(nameEditText.getText().toString().matches("") || ingredientsEditText.getText().toString().matches("") ||
                    recipeEditText.getText().toString().matches("") || caloriesEditText.getText().toString().matches("")) {
                Toast.makeText(getApplicationContext(), "Meal Not Saved!", Toast.LENGTH_SHORT).show();
            } else {
                if (isNewMeal) {
                    Thread addnewMealThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase.mealDao().insertMealTuples(meal);
                        }
                    });

                    addnewMealThread.start();

                    try {
                        addnewMealThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    needsToSave = false;
                    isNewMeal = false;

                    Toast.makeText(getApplicationContext(), "Meal Saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Thread updateMealThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            appDatabase.mealDao().updateMealTuples(meal);
                        }
                    });

                    updateMealThread.start();

                    try {
                        updateMealThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    needsToSave = false;

                    Toast.makeText(getApplicationContext(), "Meal Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
