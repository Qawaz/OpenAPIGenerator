package main

type Error struct {
	Error string `json:"error" bson:"error"`
	Message string `json:"message" bson:"message"`
}