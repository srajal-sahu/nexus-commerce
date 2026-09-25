package com.nexus.commerce.config;

import com.nexus.commerce.domain.Category;
import com.nexus.commerce.domain.Product;
import com.nexus.commerce.repository.CategoryRepository;
import com.nexus.commerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() > 0) {
            log.info("Database already contains data, skipping seed.");
            return;
        }

        log.info("Seeding realistic sample catalog data...");

        Category electronics = Category.builder()
                .name("Electronics")
                .slug("electronics")
                .description("Laptops, computers, displays and computing gear")
                .active(true)
                .build();

        Category audio = Category.builder()
                .name("Audio & Headphones")
                .slug("audio-headphones")
                .description("Premium wireless headphones, earbuds, and audio monitors")
                .active(true)
                .build();

        Category peripherals = Category.builder()
                .name("Peripherals & Keyboards")
                .slug("peripherals-keyboards")
                .description("Custom mechanical keyboards, ergonomic mice, and desk gear")
                .active(true)
                .build();

        Category wearables = Category.builder()
                .name("Wearables & Smartwatches")
                .slug("wearables-smartwatches")
                .description("Fitness trackers, adventure smartwatches, and accessories")
                .active(true)
                .build();

        categoryRepository.saveAll(List.of(electronics, audio, peripherals, wearables));

        List<Product> products = List.of(
                Product.builder()
                        .name("Sony WH-1000XM5 Wireless Headphones")
                        .slug("sony-wh-1000xm5-wireless-headphones")
                        .description("Industry-leading noise cancellation with 2 processors, 8 microphones, and 30-hour battery life.")
                        .sku("SNY-WH1000XM5-BLK")
                        .price(new BigDecimal("399.99"))
                        .stockQuantity(45)
                        .active(true)
                        .category(audio)
                        .build(),

                Product.builder()
                        .name("Apple AirPods Pro (2nd Gen) USB-C")
                        .slug("apple-airpods-pro-2nd-gen-usb-c")
                        .description("Active Noise Cancellation with Transparency mode, Spatial Audio, and MagSafe USB-C case.")
                        .sku("APL-APP2-USBC")
                        .price(new BigDecimal("249.00"))
                        .stockQuantity(80)
                        .active(true)
                        .category(audio)
                        .build(),

                Product.builder()
                        .name("MacBook Pro 16-inch M3 Max")
                        .slug("macbook-pro-16-inch-m3-max")
                        .description("Liquid Retina XDR display, 36GB Unified Memory, 1TB SSD, Space Black finish.")
                        .sku("APL-MBP16-M3MAX")
                        .price(new BigDecimal("3499.00"))
                        .stockQuantity(12)
                        .active(true)
                        .category(electronics)
                        .build(),

                Product.builder()
                        .name("Dell XPS 15 4K OLED")
                        .slug("dell-xps-15-4k-oled")
                        .description("13th Gen Intel Core i9, 32GB DDR5, 1TB NVMe, RTX 4070, 3.5K OLED InfinityEdge.")
                        .sku("DLL-XPS15-OLED")
                        .price(new BigDecimal("2199.00"))
                        .stockQuantity(20)
                        .active(true)
                        .category(electronics)
                        .build(),

                Product.builder()
                        .name("Keychron Q1 Pro Wireless Custom Mechanical Keyboard")
                        .slug("keychron-q1-pro-wireless-keyboard")
                        .description("CNC aluminum body, QMK/VIA programmable, hot-swappable K Pro Banana switches.")
                        .sku("KCR-Q1PRO-BANANA")
                        .price(new BigDecimal("199.00"))
                        .stockQuantity(35)
                        .active(true)
                        .category(peripherals)
                        .build(),

                Product.builder()
                        .name("Logitech MX Master 3S Wireless Mouse")
                        .slug("logitech-mx-master-3s-mouse")
                        .description("Quiet clicks, 8K DPI any-surface sensor, MagSpeed electromagnetic scrolling.")
                        .sku("LOG-MXM3S-GRAPH")
                        .price(new BigDecimal("99.99"))
                        .stockQuantity(110)
                        .active(true)
                        .category(peripherals)
                        .build(),

                Product.builder()
                        .name("Apple Watch Ultra 2 GPS + Cellular 49mm")
                        .slug("apple-watch-ultra-2-titanium")
                        .description("Rugged titanium case, precision dual-frequency GPS, up to 36 hours of battery life.")
                        .sku("APL-WCH-ULTRA2")
                        .price(new BigDecimal("799.00"))
                        .stockQuantity(0) // Out of stock for testing stock filter
                        .active(true)
                        .category(wearables)
                        .build(),

                Product.builder()
                        .name("Garmin Fenix 7 Pro Sapphire Solar")
                        .slug("garmin-fenix-7-pro-sapphire-solar")
                        .description("Multisport GPS watch with scratch-resistant Power Sapphire solar charging lens.")
                        .sku("GRM-FNX7PRO-SLR")
                        .price(new BigDecimal("899.99"))
                        .stockQuantity(18)
                        .active(true)
                        .category(wearables)
                        .build()
        );

        productRepository.saveAll(products);
        log.info("Catalog seeding complete: 4 categories and 8 products registered.");
    }
}
