import com.github.x7231636b.drinksmartbackend.entity.DrinkDataEntity

data class DrinkData(val id: String?, val userName: String, val timeStamp: Long, val volume: Long) {
  companion object {
    fun fromEntity(entity: DrinkDataEntity): DrinkData {
      return DrinkData(entity.id.toString(), entity.userName, entity.timeStamp, entity.volume)
    }
  }
}
