package com.example.intouch;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BusinessContact extends Contact implements Parcelable, Serializable{
	protected String linkedInURL; //in business contacts only
	private static final long serialVersionUID = 1L;
	public BusinessContact(String cat, String name, String number, String email, String url){
		super(cat, name, number, email);
		this.linkedInURL = url;
	}

	public BusinessContact(Parcel input) {
		super(input);
		this.linkedInURL = input.readString();
	}

	public String getCategory() {return category;}
	public String getName() {return name;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getEmail() {return email;}
	public String getLinkedInURL() {return linkedInURL;} //in business contact only}

	public void setLinkedInURL(String newURL) {
		linkedInURL = newURL;
		this.changed = true;
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
		dest.writeString(this.linkedInURL);
		dest.writeInt(this.signature);

	}

	public static final Parcelable.Creator<BusinessContact> CREATOR =
			new Parcelable.Creator<BusinessContact>() {
		public BusinessContact createFromParcel(Parcel in) {
			return new BusinessContact(in);
		}

		public BusinessContact[] newArray(int size) {
				return new BusinessContact[size];
		}
	};
}