package com.techzealot.transform

class Worker {
    def work() {
        println("get work done")
    }

    def analyze() {
        println("analyze")
    }

    def writeReport() {
        println("get report written")
    }
}

class Expert {
    def analyze() {
        println("expert analyze")
    }
}

class Manager {
    @Delegate
    Expert expert = new Expert()
    @Delegate
    Worker worker = new Worker()
}

def manager = new Manager()
manager.work()
manager.analyze()
manager.writeReport()
