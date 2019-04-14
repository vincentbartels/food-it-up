/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vincentbartels;


import com.vincentbartels.model.Receipt;
import com.vincentbartels.model.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class ApplicationController {

    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);


    public static void main(String[] args) {
        SpringApplication.run(ApplicationController.class, args);
    }

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @Bean
    public CommandLineRunner demo(ReceiptRepository receiptRepository) {
        return (args) -> {
            receiptRepository.save(new Receipt("lentil soup", "Cook lentils until mushy"));
            receiptRepository.save(new Receipt("curry", "cook a lot of rice with no spices"));
            receiptRepository.save(new Receipt("veggi soup", "prepare veggies with meat"));

            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Receipt customer : receiptRepository.findAll()) {
                log.info(customer.toString());
            }

        };
    }

}


