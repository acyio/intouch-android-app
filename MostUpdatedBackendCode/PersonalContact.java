package com.example.ee461l_project;


import android.os.Parcel;
import android.os.Parcelable;

public class PersonalContact extends Contact implements Parcelable {

	public PersonalContact(String cat, String name, String number, String email) {
		super(cat, name, number, email);
	}

	public PersonalContact(Parcel input) {
		super(input);
	}


	@Override
	public int describeContents() {
		return 0;
	}



	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.category);
		dest.writeString(this.name);
		dest.writeString(this.email);
		dest.writeString(this.phoneNumber);
		dest.writeInt(this.signature);
	}

	public static final Parcelable.Creator<PersonalContact> CREATOR =
			new Parcelable.Creator<PersonalContact>() {
		public PersonalContact createFromParcel(Parcel in) {
			return new PersonalContact(in);
		}

		public PersonalContact[] newArray(int size) {
			return new PersonalContact[size];
		}
	};



}