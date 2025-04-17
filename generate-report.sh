#!/bin/bash

cd "$(dirname "$0")"

timestamp=$(date +%Y-%m-%d-%H-%M)
report_dir="reports/$timestamp"

# Write environment info
mkdir -p target/allure-results
cat <<EOL > target/allure-results/environment.properties
Test Date=$(date +%Y-%m-%d)
Tester=Muhammad
EOL

# Run tests
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml

# Generate Allure report
mvn allure:report \
  -Dallure.results.directory=$(pwd)/target/allure-results \
  -Dallure.report.directory=$(pwd)/$report_dir

# Open if exists
if [ -f "$report_dir/index.html" ]; then
  echo "✅ Report created at $report_dir"
  open "$report_dir/index.html"
else
  echo "❌ Report not generated"
fi
