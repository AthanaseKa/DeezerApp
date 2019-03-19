package fr.athanase.backend.pojo.api

internal class ApiListResultJson<T>(
    var data: List<T>?,
    var total: Int?
)