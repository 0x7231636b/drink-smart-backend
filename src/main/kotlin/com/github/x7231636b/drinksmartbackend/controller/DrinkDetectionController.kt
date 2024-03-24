package com.github.x7231636b.drinksmartbackend.controller

import com.github.x7231636b.drinksmartbackend.entity.DrinkData
import com.github.x7231636b.drinksmartbackend.service.DrinkDetectionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/drinkdetection")
class DrinkDetectionController(private val drinkDetectionService: DrinkDetectionService) {

  @PostMapping("/add")
  fun postDrinkData(@RequestBody drinkData: DrinkData): ResponseEntity<String> {
    System.out.println("Received data: $drinkData")
    drinkDetectionService.saveDrinkData(drinkData)
    return ResponseEntity.ok("Data received successfully")
  }

  @GetMapping("/get")
  fun getDrinkData(@RequestParam userName: String): ResponseEntity<List<DrinkData>> {
    System.out.println("Getting data for user: $userName")
    var list = drinkDetectionService.getDrinkData(userName)

    return ResponseEntity.ok(list)
  }
}
