package hu.naturlecso.spacexrockets.common.entity

interface Mapper<E, T> {

    fun map(model: E): T

    fun mapList(modelList: List<E>?): List<T> {
        return modelList?.let { it.map { response -> map(response) } } ?: emptyList()
    }

    fun mapOptional(model: E?): Optional<T> {
        return model?.let { Optional(map(it)) } ?: Optional.empty()
    }
}
