import os
import signal
from typing import Tuple, List

from pexpect import popen_spawn
from pexpect.popen_spawn import PopenSpawn

class PolishAnalyzer(object):

    def __init__(self):
        self.analyzer: PopenSpawn = self.__run_analyzer()

    def __run_analyzer(self) -> PopenSpawn:
        dir = os.path.dirname(os.path.realpath(__file__))
        jar_file = os.path.join(dir, 'target/polish-simple-analyzer.jar').replace('\\', '/')
        process: PopenSpawn = popen_spawn.PopenSpawn('java -jar %s' % (jar_file,), encoding='utf-8')
        return process

    def analyze(self, sentence: str) -> Tuple[List, List]:
        escaped = sentence.encode('unicode-escape').decode('ascii').replace('\\x', '\\u00')
        self.analyzer.sendline(escaped)
        tokens = self.analyzer.readline().strip().encode().decode('unicode-escape')
        lemmas = self.analyzer.readline().strip().encode().decode('unicode-escape')
        return tokens.strip().split(), lemmas.strip().split()

    def close(self):
        self.analyzer.kill(signal.SIGTERM)


if __name__ == '__main__':
    analyzer = PolishAnalyzer()
    tokens, lemmas = analyzer.analyze("Zażółcić gęślą jaźń.")
    print(tokens)
    print(lemmas)
    analyzer.close()