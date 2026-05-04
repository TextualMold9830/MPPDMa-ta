package textualmold9830.mata;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.hero.Hero;
import com.watabou.pixeldungeon.items.weapon.melee.ShortSword;
import com.watabou.pixeldungeon.items.weapon.missiles.Dart;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.scenes.CellSelector;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Mata extends ShortSword {
    private static CellSelector.Listener arrowThrower = new CellSelector.Listener(){

        @Override
        public void onSelect(@Nullable Integer cell) {
            if (cell != null) {
                int limit = Random.Int(1,12);
                limit *= Random.Int(1,5);
                for (int i = 0; i < limit; i++) {
                    new Dart().cast(curUser, cell);
                }
            }
        }

        @Override
        public @Nullable String prompt() {
            return "Choose a direction to shoot at";
        }
    };
    private static CellSelector.Listener wallBreaker = new CellSelector.Listener() {
        @Override
        public void onSelect(@Nullable Integer target) {
            if (target != null) {
             if (Dungeon.level.map[target] == Terrain.WALL || Dungeon.level.map[target] == Terrain.WALL_DECO){
                 Level.set(target, Terrain.EMPTY);
                 curUser.spendAndNext(3f);
             }
            }
        }

        @Override
        public @Nullable String prompt() {
            return "Select a wall to break";
        }
    };


    private static final String AC_BREAK = "BREAK";
    {
        name = "Mă-ta";
        defaultAction = AC_THROW;
    }

    @Override
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        actions.add(AC_BREAK);
        return actions;
    }

    @Override
    public void execute(Hero hero, String action) {
        curUser = hero;
        if (action.equals(AC_BREAK)){
            GameScene.selectCell( hero,  wallBreaker );

        } else if (action.equals(AC_THROW)) {
            GameScene.selectCell( hero,  arrowThrower );

        } else {
            super.execute(hero, action);
        }
    }
}
