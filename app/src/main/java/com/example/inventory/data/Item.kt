/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * Anda tidak diperbolehkan menggunakan file ini kecuali sesuai dengan ketentuan lisensi.
 * Anda dapat memperoleh salinan lisensinya di:
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Kecuali diwajibkan oleh hukum atau disetujui secara tertulis, file ini
 * disediakan dalam kondisi "APA ADANYA" tanpa jaminan apa pun, baik tersurat maupun tersirat.
 * Lihat lisensi untuk informasi lebih lanjut.
 */

package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Kelas data (data class) yang merepresentasikan satu entitas atau baris di dalam tabel basis data.
 *
 * Anotasi @Entity menandai kelas ini sebagai entitas dalam Room dan secara otomatis
 * menghubungkannya ke tabel "items" di dalam basis data.
 *
 * @property id Kunci utama untuk tabel `items`, diatur untuk digenerasikan secara otomatis oleh database.
 * @property name Nama dari item yang disimpan.
 * @property price Harga dari item.
 * @property quantity Jumlah item yang tersedia dalam stok.
 */
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,          // ID item, diatur sebagai primary key yang digenerasikan otomatis
    val name: String,          // Nama item
    val price: Double,         // Harga item
    val quantity: Int          // Jumlah item dalam stok
)
