package main

type StackTraceElem struct {
	Is_native_method bool `json:"is_native_method" bson:"is_native_method"`
	Method_name string `json:"method_name" bson:"method_name"`
	File_name string `json:"file_name" bson:"file_name"`
	Line_number int `json:"line_number" bson:"line_number"`
	Class_name string `json:"class_name" bson:"class_name"`
}