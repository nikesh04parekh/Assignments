package com.example.repository;

import com.example.beans.Match;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
@Setter
public class MatchRepository {

    private List<Match> matchList = new ArrayList<> ();

    public Match getMatch(int matchIndex) throws IndexOutOfBoundsException{

        if (matchIndex >= matchList.size())
            throw new IndexOutOfBoundsException();
        return matchList.get(matchIndex);
    }

    public void addMatch(Match match){
        if (matchList.size() == 0)
            matchList = new ArrayList<> ();
        matchList.add(match);
    }
}
