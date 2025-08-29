# Prices Service

Implementación del enunciado **TestJava2025**.

## Arquitectura
- **Dominio (core)**: modelo `Price` y puertos (`QueryApplicablePriceUseCase`, `LoadPricesPort`).
- **Aplicación**: servicio `QueryApplicablePriceService` orquesta el caso de uso.
- **Adaptador de entrada**: `PriceController` (REST, GET `/api/prices`).
- **Adaptador de salida**: JPA/H2 (`PriceEntity`, `PriceJpaRepository`, `PriceJpaAdapter`).

## Endpoint
`GET /api/prices?applicationDate=yyyy-MM-dd-HH.mm.ss&productId=<id>&brandId=<id>`

- Acepta también formato ISO `yyyy-MM-dd'T'HH:mm:ss`.
- **Retorno**: `productId`, `brandId`, `priceList`, `startDate`, `endDate`, `price`, `currency`.
- 404 si no existe precio aplicable.

## Tests de integración
Incluye los **5 tests** del enunciado usando `MockMvc`.

## Ejecutar
```bash
mvn spring-boot:run
```

### Ejemplo
```bash
curl "http://localhost:8080/api/prices?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1"
```
