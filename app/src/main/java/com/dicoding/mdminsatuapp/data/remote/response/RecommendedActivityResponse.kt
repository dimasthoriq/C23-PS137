package com.dicoding.mdminsatuapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendedActivityResponse(

	@field:SerializedName("RecommendedActivityResponse")
	val recommendedActivityResponse: List<RecommendedActivityResponseItem>
)

data class RecommendedActivityResponseItem(

	@field:SerializedName("act_name")
	val actName: String,

	@field:SerializedName("image_filename")
	val imageFilename: String,

	@field:SerializedName("time_start")
	val timeStart: String,

	@field:SerializedName("act_id")
	val actId: Int,

	@field:SerializedName("latitude")
	val latitude: Double,

	@field:SerializedName("kuota")
	val kuota: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("time_end")
	val timeEnd: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("community")
	val community: String,

	@field:SerializedName("longitude")
	val longitude: Double
)
