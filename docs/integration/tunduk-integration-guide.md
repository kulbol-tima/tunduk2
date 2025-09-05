# Tunduk Integration Guide

This document explains the structure of the Tunduk (X-Road) integration module.

## Overview

The integration module is designed to communicate with various external government services via the Tunduk platform. It is located in the `kg.gov.mtsdr.ubk.integration.tunduk` package.

## Core Components

1.  **Configuration (`application-tunduk.yml`)**
    - This file contains all connection details for the Tunduk services.
    - Each service has a key (e.g., `get-person-info`) and properties like `service-code`, `member-code`, etc.
    - This file is activated by running the application with the `tunduk` Spring profile.

2.  **X-Road Client (`XRoadClient.java`)**
    - This is the central client for making all Tunduk requests.
    - It automatically adds the required X-Road headers to each request.
    - It includes a retry mechanism to handle transient network errors.

3.  **Adapters (`/adapter` package)**
    - An adapter class exists for each external organization (e.g., `GrsAdapter`, `SfAdapter`).
    - These adapters provide a clean, high-level API for interacting with the external services.
    - They are responsible for creating the request DTO, calling the `XRoadClient`, and mapping the response using a Mapper.

4.  **Mappers (`/mapper` package)**
    - Mapper classes transform the raw response DTOs from Tunduk into a format that is usable by the core application logic.

## Adding a New Service

To add an integration with a new Tunduk service:
1.  Add the service's configuration under the `tunduk.services` key in `application-tunduk.yml`.
2.  Create the specific Request and Response DTOs for the new service in the `/dto` package.
3.  Add a new method to the appropriate Adapter class (e.g., add a new method to `GrsAdapter` if it's a GRS service).
4.  Add a new mapping method to the corresponding Mapper class.
