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

import android.content.Context

/**
 * Container aplikasi untuk Dependency Injection.
 *
 * Interface ini mendefinisikan kontrak untuk container yang akan menyediakan dependency aplikasi.
 * Interface ini menyimpan referensi ke [ItemsRepository], yang akan disuntikkan (injected) ke dalam
 * komponen yang membutuhkan.
 */
interface AppContainer {
    // Properti abstrak ini akan berisi instance `ItemsRepository` yang harus disediakan oleh implementasi dari interface ini.
    val itemsRepository: ItemsRepository
}

/**
 * Implementasi [AppContainer] yang menyediakan instance dari [OfflineItemsRepository].
 *
 * Kelas ini merupakan implementasi konkret dari `AppContainer` yang menyediakan dependency
 * yang diperlukan oleh aplikasi. Kelas ini bertanggung jawab untuk membuat dan mengelola
 * instance dari `OfflineItemsRepository`.
 */
class AppDataContainer(private val context: Context) : AppContainer {

    /**
     * Inisialisasi `itemsRepository` secara lazy (tertunda).
     *
     * Properti ini menginisialisasi `OfflineItemsRepository` secara lazy dengan memanggil
     * `InventoryDatabase.getDatabase(context).itemDao()` untuk mengakses database dan mendapatkan
     * `ItemDao` yang diperlukan untuk membuat repositori. Penggunaan `by lazy` memastikan
     * bahwa repositori hanya akan dibuat ketika pertama kali diakses, sehingga mengoptimalkan penggunaan sumber daya.
     */
    override val itemsRepository: ItemsRepository by lazy {
        // Membuat instance dari OfflineItemsRepository dengan menggunakan itemDao dari database
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
