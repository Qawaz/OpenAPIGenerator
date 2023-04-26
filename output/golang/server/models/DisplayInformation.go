package models

type DisplayInformation struct {
	Orientation string `json:"orientation" bson:"orientation"`
	Top int `json:"top" bson:"top"`
	Left int `json:"left" bson:"left"`
	Bottom int `json:"bottom" bson:"bottom"`
	Right int `json:"right" bson:"right"`
	DisplayName string `json:"display_name" bson:"display_name"`
}