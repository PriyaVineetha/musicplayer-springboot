/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 */

// Write your code here

package com.example.song.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.song.repository.*;
import com.example.song.model.*;


// Write your code here
@Service
public class SongJpaService implements SongRepository{

    @Autowired
    private SongJpaRepository songJR;

    @Override
    public ArrayList<Song> getSongs(){
        ArrayList<Song> songsList = new ArrayList<>(songJR.findAll());
        return songsList;
    }

    @Override
    public Song addSong(Song song){
        Song newsong = songJR.save(song);
        return newsong;
    }

    @Override
    public Song getSong(int songId){
        try{
            Song song = songJR.findById(songId).get();
            return song;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song updateSong(int songId, Song song){
        try{
            Song newSong = songJR.findById(songId).get();
            if (song.getSongName() != null) newSong.setSongName(song.getSongName());
            if (song.getLyricist() != null) newSong.setLyricist(song.getLyricist());
            if(song.getSinger() != null) newSong.setSinger(song.getSinger());
            if(song.getMusicDirector() != null) newSong.setMusicDirector(song.getMusicDirector());
            songJR.save(newSong);
            return newSong;
        } catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteSong(int songId){
        try{
            songJR.deleteById(songId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
