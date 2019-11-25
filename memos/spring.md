---
theme: "white"
transition: "slide"
slideNumber: true
highlightTheme: "monokai"
---

<!-- overwrite css-->
<style type="text/css">
    .reveal h1,
    .reveal h2,
    .reveal h3,
    .reveal h4,
    .reveal h5,
    .reveal h6 {
        text-transform: none;
    }
    .reveal {
        font-size: 200%;
    }
    .reveal ol li {
        font-size: 85%;
    }
    .reveal ul li {
        font-size: 85%;
    }
    .reveal section img {
        border: none;
        box-shadow: none;
    }
    .reveal .grid-2x1 {
        display: grid;
        align-content: center;
        justify-content: center;
        align-items: center;
        justify-items: center;
        margin: auto;
        grid-column-gap: 0%;
        grid-template-columns: 45% 45%;
    }
</style>

# Spring Memo
-------------
Kazuki Motohashi @ Konduit KK

--

## Agenda

- Spring Framework
- Spring Boot
- Spring Cloud
- Spring Cloud Data Flow

--

## Spring Framework

- provides patterns and structure
- handles common things that most developers need to do:
  - creates and manages singleton instances with application context
  - builds objects relation graph by dependency injection
  - provides database connectivity
  - web applications skeleton (Spring MVC)

--

![spring](spring.png)

--

## Spring Boot

-

--

## Spring Cloud

--

## Spring Cloud Data Flow

- management tool to define pipeline of components implemented using Spring Cloud Stream (streaming) and/or Spring Task (batch), deploy/undeploy applications, etc
- provides both CLI and GUI (based on Spring Flo)
- bash-like DSL (Domain Specific Language)

`http --port=8181 | log`

- 