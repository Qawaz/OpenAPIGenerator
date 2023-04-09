package main

type UserInformation struct {
	UserEmail string `json:"user_email" bson:"user_email"`
	UserId string `json:"user_id" bson:"user_id"`
	UserName string `json:"user_name" bson:"user_name"`
}