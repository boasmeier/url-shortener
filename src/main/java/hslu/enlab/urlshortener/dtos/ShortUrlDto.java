package hslu.enlab.urlshortener.dtos;

import java.util.UUID;

public record ShortUrlDto(UUID id, String shortUrl, String url) { }
