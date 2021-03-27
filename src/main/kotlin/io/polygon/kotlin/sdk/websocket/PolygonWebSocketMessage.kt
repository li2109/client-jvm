package io.polygon.kotlin.sdk.websocket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Messages that Polygon web sockets might return. See https://polygon.io/sockets for documentation
 */
sealed class PolygonWebSocketMessage {

    data class RawMessage(var data: ByteArray) : PolygonWebSocketMessage()

    @Serializable
    data class StatusMessage(
        var ev: String? = null,
        var status: String? = null,
        var message: String? = null
    ) : PolygonWebSocketMessage()

    sealed class StocksMessage : PolygonWebSocketMessage() {

        @Serializable
        data class Trade(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("sym") var ticker: String? = null,
            @SerialName("x") var exchangeId: Long? = null,
            @SerialName("i") var tradeId: String? = null,
            @SerialName("z") var tape: String? = null,
            @SerialName("p") var price: Double? = null,
            @SerialName("s") var size: Double? = null,
            @SerialName("c") var conditions: List<Int> = emptyList(),
            @SerialName("t") var timestampMillis: Long? = null
        ) : StocksMessage()

        @Serializable
        data class Quote(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("sym") var ticker: String? = null,
            @SerialName("bx") var bidExchangeId: Long? = null,
            @SerialName("bp") var bidPrice: Double? = null,
            @SerialName("bs") var bidSize: Double? = null,
            @SerialName("ax") var askExchangeId: Long? = null,
            @SerialName("ap") var askPrice: Double? = null,
            @SerialName("as") var askSize: Double? = null,
            @SerialName("c") var condition: Int? = null,
            @SerialName("t") var timestampMillis: Long? = null
        ) : StocksMessage()

        @Serializable
        data class Aggregate(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("sym") var ticker: String? = null,
            @SerialName("v") var volume: Double? = null,
            @SerialName("av") var accumulatedVolume: Double? = null,
            @SerialName("op") var officialOpenPrice: Double? = null,
            @SerialName("vw") var volumeWeightedAveragePrice: Double? = null,
            @SerialName("o") var openPrice: Double? = null,
            @SerialName("c") var closePrice: Double? = null,
            @SerialName("h") var highPrice: Double? = null,
            @SerialName("l") var lowPrice: Double? = null,
            @SerialName("a") var averagePrice: Double? = null,
            @SerialName("s") var startTimestampMillis: Long? = null,
            @SerialName("e") var endTimestampMillis: Long? = null
        ) : StocksMessage()
    }

    sealed class ForexMessage : PolygonWebSocketMessage() {

        @Serializable
        data class Quote(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("p") var currencyPair: String? = null,
            @SerialName("x") var exchangeId: Long? = null,
            @SerialName("a") var askPrice: Double? = null,
            @SerialName("b") var bidPrice: Double? = null,
            @SerialName("t") var timestampMillis: Long? = null
        ) : ForexMessage()

        @Serializable
        data class Aggregate(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("pair") var currencyPair: String? = null,
            @SerialName("o") var openPrice: Double? = null,
            @SerialName("c") var closePrice: Double? = null,
            @SerialName("h") var highPrice: Double? = null,
            @SerialName("l") var lowPrice: Double? = null,
            @SerialName("v") var volumeInAgg: Double? = null,
            @SerialName("s") var startTimestampMillis: Long? = null,
            @SerialName("e") var endTimestampMillis: Long? = null
        ) : ForexMessage()
    }

    sealed class CryptoMessage : PolygonWebSocketMessage() {

        @Serializable
        data class Quote(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("pair") var cryptoPair: String? = null,
            @SerialName("lp") var lastTradePrice: Double? = null,
            @SerialName("ls") var lastTradeSize: Double? = null,
            @SerialName("bp") var bidPrice: Double? = null,
            @SerialName("bs") var bidSize: Double? = null,
            @SerialName("ap") var askPrice: Double? = null,
            @SerialName("as") var askSize: Double? = null,
            @SerialName("t") var exchangeTimesampeMillis: Long? = null,
            @SerialName("x") var exchangeId: Long? = null,
            @SerialName("r") var receivedAtPolygonTimestamp: Long? = null
        ) : CryptoMessage()

        @Serializable
        data class Trade(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("pair") var cryptoPair: String? = null,
            @SerialName("p") var price: Double? = null,
            @SerialName("s") var size: Double? = null,
            @SerialName("c") var conditions: List<Int> = emptyList(),
            @SerialName("i") var tradeId: String? = null,
            @SerialName("t") var exchangeTimestampMillis: Long? = null,
            @SerialName("x") var exchangeId: Long? = null,
            @SerialName("r") var receivedAtPolygonTimestamp: Long? = null
        ) : CryptoMessage()

        @Serializable
        data class Aggregate(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("pair") var cryptoPair: String? = null,
            @SerialName("o") var openPrice: Double? = null,
            @SerialName("ox") var openExchangeId: Long? = null,
            @SerialName("h") var highPrice: Double? = null,
            @SerialName("hx") var highExchangeId: Long? = null,
            @SerialName("l") var lowPrice: Double? = null,
            @SerialName("lx") var lowExchangeId: Long? = null,
            @SerialName("c") var closePrice: Double? = null,
            @SerialName("cx") var closeExchangeId: Long? = null,
            @SerialName("v") var volumeInAgg: Double? = null,
            @SerialName("s") var aggStartTimestamp: Long? = null,
            @SerialName("e") var aggEndTimestamp: Long? = null
        ) : CryptoMessage()

        @Serializable
        data class ConsolidatedQuote(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("pair") var cryptoPair: String? = null,
            @SerialName("as") var askSize: Double? = null,
            @SerialName("ap") var askPrice: Double? = null,
            @SerialName("ax") var askExchangeId: Long? = null,
            @SerialName("bs") var bidSize: Double? = null,
            @SerialName("bp") var bidPrice: Double? = null,
            @SerialName("bx") var bidExchangeId: Long? = null,
            @SerialName("t") var timestamp: Long? = null
        ) : CryptoMessage()

        @Serializable
        data class Level2Tick(
            @SerialName("ev") var eventType: String? = null,
            @SerialName("pair") var cryptoPair: String? = null,
            @SerialName("b") var bidPrices: List<List<Double>> = emptyList(),
            @SerialName("a") var askPrices: List<List<Double>> = emptyList(),
            @SerialName("t") var timestampMillis: Long? = null,
            @SerialName("x") var exchangeId: Long? = null,
            @SerialName("r") var receivedAtPolygonTimestamp: Long? = null
        ) : CryptoMessage()
    }
}