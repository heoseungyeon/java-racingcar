package racing;

import java.util.InputMismatchException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import racing.domain.Car;
import racing.domain.RacingCars;
import racing.exception.NegativeNumberException;
import racing.view.InputView;
import racing.view.ResultView;

public class RacingGame {

	private static final String CAR_COUNT_QUESTION = "자동차 대수는 몇 대 인가요?";
	private static final String CAR_MOVE_COUNT_QUESTION = "자동차 갯수는 몇 대 인가요?";
	private final InputView inputView;
	private final ResultView resultView;
	private int carCount;
	private int carMoveCount;
	private RacingCars racingCars;

	public RacingGame(InputView inputView, ResultView resultView) {
		this.inputView = inputView;
		this.resultView = resultView;
	}

	public RacingGame() {
		this(new InputView(), new ResultView());
	}

	public static void main(String[] args) {
		RacingGame racingGame = new RacingGame();
		racingGame.start();
	}

	private void start() {
		try {
			carCount = this.inputView.askCountQuestion(CAR_COUNT_QUESTION);
			carMoveCount = this.inputView.askCountQuestion(CAR_MOVE_COUNT_QUESTION);
		} catch (InputMismatchException | NegativeNumberException exception) {
			System.out.println(exception.getMessage());
			quit();
		}

		racingCars = new RacingCars(IntStream.range(0, carCount).
			mapToObj(i -> new Car()).
			collect(Collectors.toList()));
	}

	private void quit() {
		System.exit(0);
	}
}
