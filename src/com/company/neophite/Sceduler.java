package com.company.neophite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Sceduler {

    private List<Process> listOfProcess;
    private HashMap<Integer, Integer> mapOfFirstIndexed;

    public Sceduler() {
        listOfProcess = new ArrayList<>();
        mapOfFirstIndexed = new HashMap<>();
    }

    public void addProcessToSceduler(Process process) {
        if (listOfProcess.size() == 0) {
            listOfProcess.add(process);
        } else {
            int firstInOfValue = getIndexOfTheNextGreaterNumber(process);
            if (firstInOfValue == -1) {
                listOfProcess.add(process);
            } else {
                if (firstInOfValue == 0) {
                    listOfProcess.add(0, process);
                } else {
                    listOfProcess.add(firstInOfValue, process);
                }
            }
        }
    }

    public int getIndexOfTheNextGreaterNumber(Process process) {
        int execTime = process.getExecTime();
        Optional<Process> pr = listOfProcess
                .stream()
                .filter(x -> execTime < x.getExecTime())
                .findFirst();
        return pr.isPresent() ? listOfProcess.indexOf(pr.get()) : -1;
    }


    public List<Process> getListOfProcess() {
        return listOfProcess;
    }

    public void printSceduler() {
        for (Process process : this.listOfProcess) {
            System.out.println(process.toString());
        }
    }


    public static void main(String[] args) {
        Sceduler sceduler = new Sceduler();
        Process process = new Process(1, 43);
        Process process1 = new Process(2, 94);
        Process process5 = new Process(3, 7);
        Process process6 = new Process(4, 64);
        Process process2 = new Process(3, 2);
        Process process3 = new Process(4, 3);
        sceduler.addProcessToSceduler(process);
        sceduler.addProcessToSceduler(process5);
        sceduler.addProcessToSceduler(process6);
        sceduler.addProcessToSceduler(process2);
        sceduler.addProcessToSceduler(process1);
        sceduler.addProcessToSceduler(process3);
        sceduler.printSceduler();

    }

}
