## Features of Spring Boot

* Spring Boot is an opinionated framework used to create Spring Framework applications quickly
* Provides curated dependencies with version management
* Provides starter dependencies for common features
  * ie: Spring Data JPA starter includes:
    * Spring Data JPA, Hibernate, HikariCP, Spring JDBC, etc
* Provides opinionated auto configuration of included features
  * ie: - when H2 database is included, Spring Boot will auto configure a H2 in-memory database.

## Spring Boot Testing

* Spring Boot Test features are enabled through the inclusion of the starter:
  * spring-boot-starter-test
* This starter provides:
  * Common testing dependencies
  * Spring Boot Testing Dependencies - (ie Testing annotations and support)
  * Spring Boot Testing Auto-Configuration

## Spring Boot Test Scope Dependencies

* The Spring Boot Test starter brings in the following Testing Libraries:
  * JUnit
  * Spring Test - Spring Framework Testing features
  * AssertJ - Fluent assertions
  * Hamcrest - Matchers for testing
  * Mockito - Mocking framework
  * JSONAssert - Assertions for JSON
  * JsonPath - XPath for JSON

## Spring Testing Context with Spring Boot

* SpringBootTest - will enable Spring Context
  * If Using JUnit 4 the following class level annotation is also required:
    * `@RunWith(SpringRunner.class)`
  * Annotation includes `@ExtendWith(SpringExtension.class)`, thus is not needed
  * By default, searches for `@SpringBootConfiguration`
    * Included with `@SpringBootApplication`
  * By default, Spring Boot will not start a web server

## Web Environment

* To enable the web environment - `@SpringBootTest(webEnvironment=<option>)`
* Web Environment Options:
  * MOCK - Default - loads mock web environment
  * RANDOM_PORT - Provides embedded web server listening on a random port. (useful to avoid port conflicts)
  * DEFINED_PORT - Provides embedded web server listening on a 8080 (default) or server.port defined in application.properties
  * NONE - No Web Environment

## Spring Boot Test Annotations

* `@TestComponent` - Stereotype for test components
* `@TestConfiguration` - Java Configuration for tests
* `@LocalServerPort` - Inject port of running server
* `@MockBean` - Inject Mockito Mock
* `@SpyBean` - Inject Mockito Spy

## Spring Boot Test Slices

* `@SpringBootTest` - will by default scan your project and bring up a full context using all available (enabled) auto configurations
  * This can be heavy and costly on more complex applications
* Spring Boot Test **Slices** - targeted lightweight configurations which do not enable the complete defined autoconfiguration
* Example: `@JsonTest` - Creates a Spring Boot configured JSON environment for Jackson (default) or Gson.
* Use `@...Test` on test class instead of `@SpringBootTest`

## Replacement for `@SpringBootTest`

* `@DataJdbcTest`, `@DataJpaTest`, `@DataLdapTest`, `@DataMongoTest`, `@DataNeo4jTest`, `@DataRedisTest`, `@JdbcTest`, `@JooqTest`, `@JsonTest`, `@RestClientTest`, `@WebFluxTest`, `@WebMvcTest`
