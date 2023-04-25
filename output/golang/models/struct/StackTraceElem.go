package main

type StackTraceElem struct {
	IsNativeMethod *bool `json:"is_native_method" bson:"is_native_method"`
	MethodName *string `json:"method_name" bson:"method_name"`
	FileName *string `json:"file_name" bson:"file_name"`
	LineNumber *int `json:"line_number" bson:"line_number"`
	ClassName *string `json:"class_name" bson:"class_name"`
}