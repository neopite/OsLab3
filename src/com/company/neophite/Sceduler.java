package com.company.neophite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Sceduler {

    private List<Process> listOfProcess;
    private static final int MAX_EXECUTION_TIME = 1000;
    private static final int COUNT_OF_PROCCESES = 64;

    public Sceduler(int countOfProcceses) {
        listOfProcess = new ArrayList<>();
        generateListOfProcceses(countOfProcceses);
    }

    public void generateListOfProcceses(int countOfProcceses){
        for (int itter = 0; itter < countOfProcceses; itter++) {
            Process process = new Process(itter,(int)(Math.random()*MAX_EXECUTION_TIME));
            this.addProcessToSceduler(process);
        }
    }

    public void addProcessToSceduler(Process process) {
        if (listOfProcess.size() == 0) {
            listOfProcess.add(process);
        } else {
            int firstInOfValue = getIndexOfTheNextGreaterNumber(process);
            if (firstInOfValue == -1) {
                listOfProcess.add(process);
                firstInOfValue = listOfProcess.size() - 1;
            } else {
                if (firstInOfValue == 0) {
                    listOfProcess.add(0, process);
                } else {

                    listOfProcess.add(firstInOfValue, process);
                }
            }
            updateTimeWaiting(firstInOfValue);
        }
    }

    public void updateTimeWaiting(int indexOfBegin){
        for (int itter = indexOfBegin; itter < listOfProcess.size(); itter++) {
            if(indexOfBegin == 0){
                listOfProcess.get(itter).setWaitTime(0);
            }else {
                Process prevProcces = listOfProcess.get(itter - 1);
                listOfProcess.get(itter).setWaitTime(prevProcces.getWaitTime() + prevProcces.getExecTime());
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

    public double avgTimeOfWaitingPerProcces(){
        double num = listOfProcess.stream().mapToDouble(x -> x.getWaitTime()).reduce(0 , Double::sum);
        return num / listOfProcess.size();
    }

    public List<Process> getListOfProcess() {
        return listOfProcess;
    }

    public void printSceduler() {
        for (Process process : this.listOfProcess) {
            System.out.println(process.toString());
        }
        System.out.println("AVG time for waiting procceses : " + avgTimeOfWaitingPerProcces() );
    }


    public static void main(String[] args) {
        Sceduler sceduler = new Sceduler(10);
        sceduler.printSceduler();
    }
}
