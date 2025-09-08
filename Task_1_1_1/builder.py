#!/usr/bin/env python3
import os
import subprocess
import sys

SRC_DIR = "src\\main\\java\\ru\\nsu\\anishchenko"
BIN_DIR = "bin"
DOC_DIR = "docs"
MAIN_CLASS = "ru.nsu.anishchenko.Main"
JAR_FILE = "heapsort.jar"


def run_cmd(cmd, error_msg):
    try:
        subprocess.run(cmd, check=True, shell=True)
    except:
        print(error_msg)
        sys.exit(1)


def compile_java():
    if not os.path.exists(SRC_DIR):
        print("Ошибка: папка src не найдена")
        sys.exit(1)
    os.makedirs(BIN_DIR, exist_ok=True)
    run_cmd(f"javac -d {BIN_DIR} {SRC_DIR}/*.java", "Ошибка компиляции")


def create_jar():
    manifest = f"Main-Class: {MAIN_CLASS}\n"
    with open("manifest.txt", "w") as f:
        f.write(manifest)
    run_cmd(f"jar cfm {JAR_FILE} manifest.txt -C {BIN_DIR} .", "Ошибка создания JAR")
    os.remove("manifest.txt")


def run():
    run_cmd(f"java -jar {JAR_FILE}", "Ошибка запуска приложения")


if __name__ == "__main__":
    compile_java()
    create_jar()
    run()
