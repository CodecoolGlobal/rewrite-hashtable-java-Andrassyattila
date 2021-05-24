package com.codecool.hashtable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HashTable<K, V> {
    // Number of all buckets - Do not modify!
    private final int bucketsSize = 16;

    private List<List<Entry>> buckets;

    private int getBucketIndexForKey(K key) {
        int counter=0;
        for(List<Entry> bucket : this.buckets){
            for(Entry<K,V> e:bucket){
                if(e.key==key){
                    return counter;
                }
            }
            counter++;
        }
        return -1;
    }

    private List<Entry> getBucketAtIndex(int position) {
        return buckets.get(position);
    }

    private Entry findEntryInBucket(K key, List<Entry> bucket) {
        //throw new RuntimeException(";
        lazyInit();
        Entry<K,V> newEntry = null;
        for(Entry buck : bucket) {
            if (buck.key == key) {
                newEntry=buck;
            }
            else {
                return null;
            }
        }
        return newEntry;
    }

    public V get(K key) {
        lazyInit();
        for(List<Entry> bucket : this.buckets){
            for(Entry<K,V> e:bucket){
                if(e.key==key){
                    return e.value;
                }
            }
        }
        return null;
    }

    public void put(K key, V value) {
        lazyInit();
        if(get(key)==null){
            int position = (int)value.toString().charAt(0);
            if(this.buckets.size()<position){
                while (this.buckets.size()<position+1){
                    this.buckets.add(new ArrayList<Entry>());
                }
            }
            getBucketAtIndex(position).add(new Entry(key,value));
        }else {
            for(List<Entry> bucket : this.buckets){
                for(Entry<K,V> e:bucket){
                    if(e.key==key){
                        e.value=value;
                    }
                }

            }
        }
    }

    public V remove(K key) {
        //throw new RuntimeException(;
        for(List<Entry> bucket : this.buckets) {
            for (Entry<K, V> e : bucket) {
                if(e.key==key){
                    bucket.clear();
                    return e.value;
                }

            }
        }

        return null;
    }

    public void clear() {
        for(List<Entry> bucket : buckets){
            bucket.clear();
        }
    }
    private void lazyInit(){
        if(this.buckets==null){
            this.buckets=new ArrayList<List<Entry>>();
        }
    }
}

class Entry<K, V> {

    public K key;
    public V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

}
