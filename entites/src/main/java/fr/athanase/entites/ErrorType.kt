package fr.athanase.entites

enum class StateErrorType(val type: String) {
    NETWORK_ERROR("NetworkError"),
    ENDPOINT_ERROR("EndpointError"),
    ERROR("Error");
}