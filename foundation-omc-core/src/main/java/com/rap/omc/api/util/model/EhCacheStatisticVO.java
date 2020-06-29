package com.rap.omc.api.util.model;
public class EhCacheStatisticVO {
    private String ehcacheName;
    private String size;
    private String localHeapSize;
    private String offHeapSize;
    private String localDiskSize;
    private String hitCount;
    private String missCount;
    
    /**
     * @param ehcacheName
     * @param size
     * @param localHeapSize
     * @param offHeapSize
     * @param localDiskSize
     * @param hitCount
     * @param missCount
     */
    public EhCacheStatisticVO(String ehcacheName, String size, String localHeapSize, String offHeapSize,
            String localDiskSize, String hitCount, String missCount) {
        super();
        this.ehcacheName = ehcacheName;
        this.size = size;
        this.localHeapSize = localHeapSize;
        this.offHeapSize = offHeapSize;
        this.localDiskSize = localDiskSize;
        this.hitCount = hitCount;
        this.missCount = missCount;
    }

    /**
     * 
     * 
     * @return the ehcacheName
     */
    public String getEhcacheName(){
        return ehcacheName;
    }
    
    /**
     * 
     * 
     * @param ehcacheName the ehcacheName to set
     */
    public void setEhcacheName(String ehcacheName){
        this.ehcacheName = ehcacheName;
    }
    
    /**
     * 
     * 
     * @return the size
     */
    public String getSize(){
        return size;
    }
    
    /**
     * 
     * 
     * @param size the size to set
     */
    public void setSize(String size){
        this.size = size;
    }
    
    /**
     * 
     * 
     * @return the localHeapSize
     */
    public String getLocalHeapSize(){
        return localHeapSize;
    }
    
    /**
     * 
     * 
     * @param localHeapSize the localHeapSize to set
     */
    public void setLocalHeapSize(String localHeapSize){
        this.localHeapSize = localHeapSize;
    }
    
    /**
     * 
     * 
     * @return the offHeapSize
     */
    public String getOffHeapSize(){
        return offHeapSize;
    }
    
    /**
     * 
     * 
     * @param offHeapSize the offHeapSize to set
     */
    public void setOffHeapSize(String offHeapSize){
        this.offHeapSize = offHeapSize;
    }
    
    /**
     * 
     * 
     * @return the localDiskSize
     */
    public String getLocalDiskSize(){
        return localDiskSize;
    }
    
    /**
     * 
     * 
     * @param localDiskSize the localDiskSize to set
     */
    public void setLocalDiskSize(String localDiskSize){
        this.localDiskSize = localDiskSize;
    }
    
    /**
     * 
     * 
     * @return the hitCount
     */
    public String getHitCount(){
        return hitCount;
    }
    
    /**
     * 
     * 
     * @param hitCount the hitCount to set
     */
    public void setHitCount(String hitCount){
        this.hitCount = hitCount;
    }
    
    /**
     * 
     * 
     * @return the missCount
     */
    public String getMissCount(){
        return missCount;
    }
    
    /**
     * 
     * 
     * @param missCount the missCount to set
     */
    public void setMissCount(String missCount){
        this.missCount = missCount;
    }

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "EhCacheStatisticVO [ehcacheName=" + ehcacheName + ", size=" + size + ", localHeapSize=" + localHeapSize
                + ", offHeapSize=" + offHeapSize + ", localDiskSize=" + localDiskSize + ", hitCount=" + hitCount
                + ", missCount=" + missCount + "]";
    }
    
}
