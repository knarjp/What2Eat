package com.design.senior.what2eat.DatabaseComponents.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by KJ on 3/6/2018.
 */

@Entity(tableName = "MealEntryJoin",
        foreignKeys =  {
                @ForeignKey(entity = Meal.class,
                            parentColumns =  "MealID",
                            childColumns =  "meal",
                            onDelete = CASCADE),
                @ForeignKey(entity = Entry.class,
                            parentColumns = "ID",
                            childColumns = "entry",
                            onDelete = CASCADE)
        },
        indices = {@Index(value = {"entry"}), @Index(value ={"meal"})
        })
public class MealEntryJoin implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "joinID", typeAffinity = ColumnInfo.INTEGER)
    private int joinID;
    @ColumnInfo(name = "meal", typeAffinity = ColumnInfo.INTEGER)
    private int meal;
    @ColumnInfo(name = "entry", typeAffinity = ColumnInfo.INTEGER)
    private int entry;

    public MealEntryJoin(final int meal, final int entry) {
        this.meal = meal;
        this.entry = entry;
    }

    public int getMeal() {
        return meal;
    }

    public int getEntry() {
        return entry;
    }

    public int getJoinID() { return joinID; }

    public void setJoinID(int joinID) {
        this.joinID = joinID;
    }

    // This code is used to allow us to pass join objects in-between intents without having
    // to manually get each of the join attributes and doing multiple passes into the intent
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(joinID);
        dest.writeInt(meal);
        dest.writeInt(entry);
    }

    private MealEntryJoin(Parcel parcel) {
        // ORDER MATTERS, SEE ABOVE METHOD FOR ORDER
        this.joinID = parcel.readInt();
        this.meal = parcel.readInt();
        this.entry = parcel.readInt();
    }

    public static final Parcelable.Creator<MealEntryJoin> CREATOR = new Parcelable.Creator<MealEntryJoin>() {
        public MealEntryJoin createFromParcel(Parcel in) {
            return new MealEntryJoin(in);
        }

        public MealEntryJoin[] newArray(int size) {
            return new MealEntryJoin[size];
        }
    };
}