package game;

import java.io.PipedReader;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.Toad;
import game.enemy.Koopa;
import game.ground.*;
import game.magical_Items.PowerStar;
import game.magical_Items.SuperMushroom;
import game.ground.trees.Sprout;

import java.util.Random;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava(), new WarpPipe());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"..CCCC......+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			List<String> lavaMap = Arrays.asList(
					"C...............................",
					".LLLLLL.........................",
					".......................LLLLL....",
					".....LL.........................",
					"........................LLLL....",
					"................................",
					"..LLLLLLLLL.....................",
					"..LLLLLLLLL.........L.LL........",
					"....................L..L........",
					"........LLL.........L..L........",
					"....................LLLL........");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			GameMap lavaGameMap = new GameMap(groundFactory, lavaMap);
			world.addGameMap(lavaGameMap);

			Player mario = new Player("Player", 'm', 100);
			//world.addPlayer(mario, gameMap.at(42, 9));
			//world.addPlayer(mario, lavaGameMap.at(6,6));
			world.addPlayer(mario, gameMap.at(1,1));

			Actor toad = new Toad();
			gameMap.at(44,10).addActor(toad);

			Random rand = new Random();

			//adding superMushroom under mario spawn
			SuperMushroom superMushroom = new SuperMushroom(true);
			gameMap.at(42, 9).addItem(superMushroom);

			//spawning in a random mushroom on map for spicyness
			SuperMushroom superMushroomRand = new SuperMushroom(true);
			gameMap.at(rand.nextInt(39), rand.nextInt(19)).addItem(superMushroomRand);

			//adding powerstar under mario spawn
			PowerStar powerStar = new PowerStar(true);
			//gameMap.at(42, 9).addItem(powerStar);
			gameMap.at(1, 1).addItem(powerStar);

			//adding a random powerstar on LHS of map
			PowerStar powerStarRand = new PowerStar(true);
			gameMap.at(rand.nextInt(39), rand.nextInt(19)).addItem(powerStarRand);

			//gameMap.at(35, 10).addActor(new Koopa());
			//gameMap.at(35, 7).addActor(new Koopa());


			PipesManager.getInstance().setWarpTo(lavaGameMap.at(0,0)); // Top left corner

			world.run();

	}
}
