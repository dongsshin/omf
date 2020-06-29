package com.rap.omc.util.core;

public class StopWatch{
    private int count = 0;
    private long start = 0L;
    private long current = 0L;
    private long max = 0L;
    private long min = 0L;
    public StopWatch()
    {
        reset();
    }
    public long getElapsed()
    {
        this.count += 1;
        long now = System.currentTimeMillis();
        long elapsed = now - this.current;
        this.current = now;
        if (elapsed > this.max) {
            this.max = elapsed;
        }
        
        if (elapsed < this.min) {
            this.min = elapsed;
        }
        return elapsed;
    }
    public int getCount()
    {
        return this.count;
    }
    public long getMinTime()
    {
        return this.min;
    }
    public long getMaxTime()
    {
        return this.max;
    }
    public long getAvgTime()
    {
        if (this.count > 0) {
            return Math.round((this.current - this.start) / this.count);
            }
        return 0L;
    }
    public long getTotalElapsed()
    {
        this.current = System.currentTimeMillis();
        return (this.current - this.start);
    }
    public long getStartTime()
    {
        return this.start;
    }
    public void reset()
    {
        this.start = System.currentTimeMillis();
        this.current = this.start;
        this.count = 0;
        this.max = 0L;
        this.min = 0L;
    }
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        
        float taskCount = getCount();
        float taskTime = (float)getTotalElapsed() / 1000.0F;
        float taskMin = (float)getMinTime() / 1000.0F;
        float taskMax = (float)getMaxTime() / 1000.0F;
        float taskAvg = (float)getAvgTime() / 1000.0F;
        buffer.append("\nCount : " + taskCount + "\nTotal : " + taskTime + "\nMax : " + taskMax + "\nMin : "
                + taskMin + "\nAvg : " + taskAvg + "\n");
        
        return buffer.toString();
    }
}
