package app.persistence.interfaces;

import app.model.Move;
import app.model.User;
import app.model.UserGameDto;
import app.model.UserWordDto;

import java.util.List;

public interface IMoveRepository extends IRepository<Integer, Move> {
    public UserWordDto[] getChosenWordsForGame(Integer gameId);

    public UserGameDto[] getDetailsUserGame(String username, Integer gameId);

    public List<Move> findForGame(int game);
}
