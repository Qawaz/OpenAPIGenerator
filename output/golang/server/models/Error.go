package models

type Error struct {
	Type string `json:"type" bson:"type"`
	Message string `json:"message" bson:"message"`
}