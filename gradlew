- name: Fix gradlew permission
  run: chmod +x ./gradlew

- name: Run Gradle version
  run: ./gradlew --version
  env:
    JAVA_HOME: ${{ env.JAVA_HOME }}