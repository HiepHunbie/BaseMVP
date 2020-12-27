package com.hiepnt.basemvp.model

data class Children (

	val id : String,
	val title : String,
	val description : String,
	val children : List<Children>,
	val childIds : List<String>,
	val status : String,
	val createdDate : String,
	val updatedDate : String,
	val createdBy : String,
	val updatedBy : String,
	val videosIds : List<String>,
	val videosLinks : List<String>,
	val videoList : String
)