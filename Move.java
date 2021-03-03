public class Move {
    public static boolean isNotNull(Position from, Position to) {
	return !from.equals(to);
    }
    
    public static boolean isHorizontal(Position from, Position to) {
	return from.getRow() == to.getRow();
    }

    public static boolean isVertical(Position from, Position to) {
	return from.getCol() == to.getCol();
    }

    public static boolean isDiagonal(Position from, Position to) {
	return Math.abs(from.getRow()-to.getRow()) == Math.abs(from.getCol()-to.getCol());
    }

    public static int horizontalLength(Position from, Position to) {
	return Math.abs(to.getCol() - from.getCol());
    }

    public static int verticalLength(Position from, Position to) {
	return Math.abs(to.getRow() - from.getRow());
    }
}
