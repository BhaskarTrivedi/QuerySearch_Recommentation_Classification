import sys


class CLog:
    def __init__(self):
        self.k = 0
    # Print iterations progress
    def progress_track(self,completed, total):
        str_format = "{0:.0f}"
        percents = str_format.format(100 * (completed / float(total)))
        filled_length = int(round(100 * completed / float(total)))
        bar = '█' * filled_length + '-' * (100 - filled_length)

        sys.stdout.write('\r |%s| %s%%' % (bar, percents)),

        if completed == total:
            sys.stdout.write('\n')
        sys.stdout.flush()