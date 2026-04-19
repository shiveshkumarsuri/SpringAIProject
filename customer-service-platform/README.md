# Customer Feedback Sentiment Analysis

## 📋 Overview

This project leverages **Java NLP libraries** and **Stanford CoreNLP** to perform comprehensive sentiment analysis on customer feedback. It processes raw customer feedback data, classifies sentiment, and generates structured output reports for business insights.

## 🎯 Objectives

- ✅ Collect and work with real customer feedback data
- ✅ Apply natural language processing (NLP) techniques using Java
- ✅ Perform sentiment analysis and classify feedback as **POSITIVE**, **NEGATIVE**, or **NEUTRAL**
- ✅ Generate structured output for further analysis and integration with Spring AI in later phases

## 📝 Key Tasks

1. **Create a Maven-based Java project** in the IDE
2. **Implement sentiment analysis logic** using Stanford CoreNLP
3. **Parse feedback entries** and classify sentiment
4. **Generate structured output** including:
   - Summary statistics
   - Department-level insights
   - Detailed entry breakdowns

## 🔧 Technology Stack

- **Language**: Java 21
- **Build Tool**: Maven
- **NLP Library**: Stanford CoreNLP 4.5.5
- **JSON Processing**: Jackson 2.16.1
- **Logging**: SLF4J + Logback
- **Testing**: JUnit 5 Jupiter

## 📁 Project Structure

```
customer-feedback/
├── customer-service-platform/
│   ├── src/main/java/com/retailstore/
│   ├── src/main/resources/
│   ├── pom.xml
│   └── README.md
├── pom.xml
└── [input/output files]
```

## 📄 Input & Output Files

### Input
- **`store_feedback.txt`**: Raw dataset containing customer feedback entries

### Output
- **`sentiment_feedback_output.txt`**: Structured output file containing:
  - Sentiment classifications (POSITIVE/NEGATIVE/NEUTRAL)
  - Summary statistics
  - Department-level insights
  - Detailed entry analysis

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+

### Build & Run
```bash
# Navigate to project directory
cd customer-service-platform

# Build the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.retailstore.App"

# Run tests
mvn test
```

## 📊 Sentiment Classification

The sentiment analysis will classify feedback into three categories:

| Sentiment | Description |
|-----------|-------------|
| **POSITIVE** | Favorable feedback, compliments, satisfaction |
| **NEGATIVE** | Critical feedback, complaints, issues |
| **NEUTRAL** | Factual statements, no clear sentiment |

## 📚 Dependencies

- Stanford CoreNLP - NLP processing and sentiment analysis
- Jackson Databind - JSON serialization/deserialization
- JUnit 5 - Unit testing framework
- Logback - Logging implementation

## 📋 Output Format

The sentiment analysis results include:
- Individual sentiment scores for each feedback entry
- Aggregated summary statistics
- Department-wise sentiment breakdown
- Detailed insights for business analysis

## 🔮 Future Enhancements

- Integration with Spring AI for advanced NLP capabilities
- Real-time feedback processing API
- Dashboard for sentiment visualization
- Multi-language sentiment analysis support
