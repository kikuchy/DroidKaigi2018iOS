package network

enum class NetworkError {
    TRAFFIC_PROBLEM,
    WRONG_STATUS_CODE,
    EMPTY_BODY,
    INVALID_JSON
}