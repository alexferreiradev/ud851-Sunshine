/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages a local database for weather data.
 */
//  (11) Extend SQLiteOpenHelper from WeatherDbHelper
public class WeatherDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "sunshine.db";

	public WeatherDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String createTableSql = "Create table " + WeatherContract.WeatherEntry.TABLE_NAME +
				"( " +
				WeatherContract.WeatherEntry._ID + " integer primary key autoincrement, " +
				WeatherContract.WeatherEntry.COLUMN_DATE + " text, " +
				WeatherContract.WeatherEntry.COLUMN_DEGREES + " real, " +
				WeatherContract.WeatherEntry.COLUMN_HUMIDITY + " real, " +
				WeatherContract.WeatherEntry.COLUMN_MAX_TEMP + " integer, " +
				WeatherContract.WeatherEntry.COLUMN_MIN_TEMP + " integer, " +
				WeatherContract.WeatherEntry.COLUMN_PRESSURE + " real, " +
				WeatherContract.WeatherEntry.COLUMN_WEATHER_ID + " integer, " +
				WeatherContract.WeatherEntry.COLUMN_WIND_SPEED + " real" +
				")";
		sqLiteDatabase.execSQL(createTableSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}

	//   (12) Create a public static final String called DATABASE_NAME with value "weather.db"

//   (13) Create a private static final int called DATABASE_VERSION and set it to 1

//   (14) Create a constructor that accepts a context and call through to the superclass constructor

//   (15) Override onCreate and create the weather table from within it

//   (16) Override onUpgrade, but don't do anything within it yet
}