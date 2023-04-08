package main

type UserInformation struct {
	User_email string `json:"user_email" bson:"user_email"`
	User_id string `json:"user_id" bson:"user_id"`
	User_name string `json:"user_name" bson:"user_name"`
}