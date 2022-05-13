package hslu.enlab.urlshortener.dtos;

import java.util.UUID;

/**
 * Code of class ShortUrlDto.
 *
 * @author Tim Honermann
 * @version JDK 17.0.2
 */
public record ShortUrlDto(UUID id, String shortUrl, String url) { }
