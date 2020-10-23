package fr.tschwebel.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.tschwebel.Server;
import fr.tschwebel.domain.Birthday;

public class BirthdayImporter {
    // E.g. data-tooltip-content=\"John Rambo (2\/14)\"
    static final String PATTERN = "data-tooltip-content=\\\\\"(.*? .*?)? \\((\\d+)\\\\/(\\d+)\\)\\\\\"";

    public static List<Birthday> readData() {
        Stream<Path> pathStream = Stream.empty();

        try {
            URL resource = Server.class.getClassLoader().getResource("birthdays");
            Path folder = Paths.get(resource.toURI());

            pathStream = Files.walk(folder)
                    .filter(Files::isRegularFile);
        } catch (URISyntaxException | IOException e) {
            System.out.println("Something went wrong while fetching birthday data, skipping.");
            e.printStackTrace();
            return Collections.emptyList();
        }

        return readPaths(pathStream);
    }

    private static List<Birthday> readPaths(Stream<Path> pathStream) {
        return pathStream
                .map(BirthdayImporter::processFile)
                .flatMap(List::stream)
                .distinct()
                .sorted(Comparator.comparing(Birthday::getMonth)
                        .thenComparing(Birthday::getDay))
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Birthday> processFile(Path p) {
        String content = "";
        try {
            content = Files.readString(p);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading data files, skipping.");
            e.printStackTrace();
            return List.of();
        }

        List<Birthday> bdays = new ArrayList<>();
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) { // unfortunately no streaming available here?
            Optional<Birthday> bdayOpt = toBday(matcher);
            bdayOpt.ifPresent(bdays::add);
        }

        return bdays;
    }

    static Optional<Birthday> toBday(Matcher matcher) {
        if (matcher.groupCount() != 3) {
            System.out.println("Warning invalid groupCount (" + matcher.groupCount() + ") for:" + matcher.group());
            return Optional.empty();
        }

        String name = matcher.group(1);
        int month = Integer.parseInt(matcher.group(2)); // by contract
        int day = Integer.parseInt(matcher.group(3)); // by contract

        return Optional.of(new Birthday(name, month, day));
    }
}
