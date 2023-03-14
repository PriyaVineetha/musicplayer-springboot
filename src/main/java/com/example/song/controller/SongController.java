/*
 * 
 * You can use the following import statements
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */

// Write your code here

package com.example.song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.song.service.*;
import com.example.song.model.*;


// Write your code here
@RestController
public class SongController{

    @Autowired
    private SongJpaService sjs;

    @GetMapping("/songs")
    public ArrayList<Song> getSongs(){
        return sjs.getSongs();
        }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song){
        return sjs.addSong(song);
    }

    @GetMapping("/songs/{songId}")
    public Song getSong(@PathVariable("songId") int songId){
        return sjs.getSong(songId);
    }

    @PutMapping("songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId, @RequestBody Song song){
        return sjs.updateSong(songId, song);
    }

    @DeleteMapping("songs/{songId}")
    public void deleteSong(@PathVariable("songId") int songId){
        sjs.deleteSong(songId);
    }
}