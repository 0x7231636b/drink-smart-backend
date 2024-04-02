package com.github.x7231636b.drinksmartbackend.controller

import DrinkData
import com.github.x7231636b.drinksmartbackend.entity.DrinkDataEntity
import com.github.x7231636b.drinksmartbackend.service.DrinkDetectionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/drinkdetection")
class DrinkDetectionController(private val drinkDetectionService: DrinkDetectionService) {

  var log: Logger = LoggerFactory.getLogger(DrinkDetectionController::class.java)

  @PostMapping("/add")
  fun postDrinkDataEntity(@RequestBody DrinkDataEntity: DrinkDataEntity): ResponseEntity<String> {
    log.info("Received data: $DrinkDataEntity")
    drinkDetectionService.saveDrinkDataEntity(DrinkDataEntity)
    return ResponseEntity.ok("Data received successfully")
  }

  @GetMapping("/get")
  fun getDrinkDataEntity(@RequestParam userName: String): ResponseEntity<List<DrinkData>> {
    log.info("Getting data for user: $userName")
    val list =
        drinkDetectionService.getDrinkDataEntity(userName).map {
          DrinkData(it.userName, it.timeStamp, it.volume, null)
        }
    log.info("Data for user: $userName is: $list")
    return ResponseEntity.ok(list)
  }

  @GetMapping("/getVolumeForDay")
  fun getVolumeForDay(
      @RequestParam userName: String,
      @RequestParam date: Long?
  ): ResponseEntity<DrinkData> {
    val dateToUse = date ?: System.currentTimeMillis()
    log.info("Getting volume for user: $userName at date: $dateToUse")
    var list = drinkDetectionService.getDrinkDataEntity(userName, dateToUse)
    var sips = list.size
    var volume = list.map { it.volume }.sum()
    log.info("Volume for user: $userName at date: $dateToUse is: $volume")
    var response = DrinkData(userName, dateToUse, volume, sips)
    return ResponseEntity.ok(response)
  }
}
