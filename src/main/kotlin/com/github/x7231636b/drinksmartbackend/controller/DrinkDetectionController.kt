package com.github.x7231636b.drinksmartbackend.controller

import DrinkData
import GetVolumeForDayResponse
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
  fun postDrinkDataEntity(@RequestBody drinkData: DrinkData): ResponseEntity<String> {
    log.info("Received data: $DrinkDataEntity")
    drinkDetectionService.saveDrinkData(drinkData)
    return ResponseEntity.ok("Data received successfully")
  }

  /** Same as POST but indicating an update to the outside world */
  @PutMapping("/update")
  fun putDrinkData(@RequestBody drinkData: DrinkData): ResponseEntity<String> {
    log.info("Changing data: $drinkData")
    drinkDetectionService.updateDrinkData(drinkData)
    return ResponseEntity.ok("Data changed successfully")
  }

  @GetMapping("/get")
  fun getDrinkDataEntity(@RequestParam userName: String): ResponseEntity<List<DrinkData>> {
    log.info("getDrinkDataEntity: data for user: $userName")
    val list = drinkDetectionService.getDrinkData(userName).map { DrinkData.fromEntity(it) }
    log.info("Found ${list.size} entries for user: $userName")
    return ResponseEntity.ok(list)
  }

  @GetMapping("/getVolumeForDay")
  fun getVolumeForDay(
      @RequestParam userName: String,
      @RequestParam date: Long?
  ): ResponseEntity<GetVolumeForDayResponse> {
    log.info("Getting volume for user: $userName at date: $date")
    var response = drinkDetectionService.getVolumeForDay(userName, date)
    log.info("Volume for user this day: $response")
    return ResponseEntity.ok(response)
  }
}
