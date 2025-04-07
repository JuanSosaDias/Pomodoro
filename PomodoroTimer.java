import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {

    private static final int work_duration = 25 * 60;   // 25 minutos en segundos
    private static final int break_duration = 5 * 60;   // 5 minutos en segundos

    private static Timer timer = new Timer();

    private static boolean isWorking = true;
    private static int secondsLeft;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Temporizador Pomodoro");
        System.out.print("¿Cuántos ciclos querés hacer? (trabajo + descanso): ");
        int cycles = scanner.nextInt();

        runPomodoro(cycles);
    }

    private static void runPomodoro(int cycles) {
        for (int i = 1; i <= cycles; i++) {
            System.out.println("\n🔔 Ciclo " + i + " - ¡A trabajar!");
            startCountdown(work_duration, "¡Terminó el trabajo! Tomate un descanso 😌");

            System.out.println("\n💤 Ciclo " + i + " - Descanso");
            startCountdown(break_duration, "¡Descanso terminado! Volvé al trabajo 💪");
        }

        System.out.println("\n✅ ¡Completaste todos los ciclos Pomodoro! 🎉");
        timer.cancel();
    }

    private static void startCountdown(int duration, String endMessage) {
        secondsLeft = duration;

        TimerTask task = new TimerTask() {
            public void run() {
                int min = secondsLeft / 60;
                int sec = secondsLeft % 60;
                System.out.printf("\r⏱️  %02d:%02d ", min, sec);

                if (--secondsLeft < 0) {
                    System.out.println("\n🔔 " + endMessage);
                    cancel(); // Termina el TimerTask
                }
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);

        // Espero hasta que el temporizador termine antes de seguir
        try {
            Thread.sleep((duration + 1) * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
