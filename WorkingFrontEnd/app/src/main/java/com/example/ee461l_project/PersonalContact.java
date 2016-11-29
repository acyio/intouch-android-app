package com.example.ee461l_project;


import android.os.Parcel;

public class PersonalContact extends Contact{

	public PersonalContact(String cat, String name, String number, String email) {
		super(cat, name, number, email);
	}
	/*
	public PersonalContact(Parcel parcel) {
		super(parcel);
		this.category = parcel.readString();
		this.name = parcel.readString();
		this.email = parcel.readString();
		this.phoneNumber = parcel.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}



	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(this.phoneNumber);
		dest.writeString(this.email);
		dest.writeString(this.category);
	}
	*/


}